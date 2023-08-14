<script lang="ts" setup>
import { useRoute } from "vue-router";
import { ref, Ref, onBeforeMount, onMounted, watch } from "vue";
import { useAuthStore } from "../../../store/authStore";
import { useNotificationStore } from "../../../store/notificationStore";
import { ModalsContainer, useModal } from "vue-final-modal";
import ModalConfirm from "../../modals/ModalConfirm.vue";
import { api } from "../../../services/http-common";
import Pagination from "../../Pagination.vue";
const notificationStore = useNotificationStore();
const authStore = useAuthStore();
const route = useRoute();
const exam = ref();
const second = ref();
const timeCountDown = ref();
const allQuestions = ref();

const userAswer = ref({
  exam: {
    examId: route.params.id,
  },
  user: {
    userId: 3,
  },
  answerDtos: [
    {
      answer: {
        answerId: "",
      },
      status: "null",
    },
  ],
});
interface ResponseData {
  last_page: number;
  current_page: number;
}
interface SearchForm {
  page: number;
  size: number;
}
const response: Ref<ResponseData> = ref({
  last_page: 1,
  current_page: 0,
});
const searchForm: Ref<SearchForm> = ref({
  page: 0,
  size: 5,
});
function timeToSeconds(time: any) {
  const parts = time.split(":");
  const hours = parseInt(parts[0]) || 0;
  const minutes = parseInt(parts[1]) || 0;
  const seconds = parseInt(parts[2]) || 0;
  return hours * 3600 + minutes * 60 + seconds;
}
function getTime() {
  const hours = Math.floor(second.value / 3600);

  const minutes = Math.floor((second.value % 3600) / 60);
  const seconds = second.value % 60;
  return `${hours}:${minutes < 10 ? "0" : ""}${minutes}:${
    seconds < 10 ? "0" : ""
  }${seconds}`;
}
const submitExam = async () => {
  try {
    const data = await api.post("user-answers/create", userAswer.value);
    console.log(data);
  } catch (e) {
    console.error(e);
  }
  console.log(userAswer.value);
  console.log(authStore.id);
};
const getExamByIdofCourse = async () => {
  const { data } = await api.get(`/course/exam/${route.params.id}`);
  exam.value = data;
  console.log(exam.value);
  second.value = timeToSeconds(exam.value.duration);
};

setInterval(() => {
  if (second.value > 0) {
    second.value--;
  }
}, 1000);
const getAllQuestions = async () => {
  try {
    const data = await api.get(`/exam/questions/exam-id=${route.params.id}`);
    allQuestions.value = data.data.content;

    allQuestions.value.forEach(() => {
      userAswer.value.answerDtos.push({
        answer: {
          answerId: 0,
        },
        status: "null",
      });
    });
    userAswer.value.answerDtos.splice(0, 1);
  } catch (e) {
    console.error(e);
  }
};
const getList = async (page: number = 0) => {
  try {
    searchForm.value.page = page;
    // @ts-ignore
    const params = new URLSearchParams(searchForm.value).toString();
    let data = await api.get(
      `/exam/questions/exam-id=${route.params.id}?${params}`
    );

    allQuestions.value = data.data.content;
    response.value.current_page = data.data.number;
    response.value.last_page = data.data.totalPages - 1;
  } catch (e) {}
};
const { open, close } = useModal({
  component: ModalConfirm,
  attrs: {
    title: "Cofirm!",

    async onConfirm() {
      try {
        submitExam();
        notificationStore.openSuccess("Submit Exam success.");
        close();
      } catch (e) {
        console.error(e);
      }
    },
    onReject() {
      close();
    },
  },
  slots: {
    default: `<p class="text-center font-semibold text-xl">
Are you sure you want to submit your essay?</p>`,
  },
});
watch(second, () => {
  timeCountDown.value = getTime();
  if (second.value === 0) {
    submitExam();
  }
});
const scrollToQuestion = (id) => {
  document.getElementById(id)?.scrollIntoView({ behavior: "smooth" });
};
onBeforeMount(async () => {
  await getExamByIdofCourse();
  await getAllQuestions();
});
onMounted(async () => {
  await getList();
  window.onpopstate = function (event) {
    if (confirm("System will automatically submit") == true) {
      submitExam();
      notificationStore.openSuccess("Submited exam");
    }
  };
});
</script>
<template>
  <div class="flex flex-row w-full">
    <form
      @submit.prevent="submitExam()"
      class="w-1/2 flex flex-col mx-auto p-5 rounded-xl border border-gray-400"
    >
      <p class="text-3xl font-semibold text-center">Exam</p>
      <div
        v-for="(question, index) in allQuestions"
        :key="question.question.questionId"
        :id="index"
        class="rounded-xl border py-5 border-gray-200 mb-4"
      >
        <div class="grid grid-cols-2 gap-6 px-5 mt-4 w-full">
          <div class="flex flex-col w-full col-span-2 text-xl font-semibold">
            <p class="font-bold text-zinc-600 px-2">Question{{ index + 1 }}:</p>
            <p class="text-red ml-4">
              {{ question.question.contentQuestion }}
            </p>
          </div>

          <div class="flex flex-col w-full">
            <p class="font-bold text-zinc-600 px-2">A</p>
            <div
              class="flex flex-row items-center space-x-2 text-lg font-semibold"
            >
              <input
                type="radio"
                :value="question.question.answer[0].answerId"
                :name="`aswer-radio-${index}`"
                v-model="userAswer.answerDtos[index].answer.answerId"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <p>{{ question.question.answer[0].contentAnswer }}</p>
            </div>
          </div>
          <div class="flex flex-col w-full">
            <p class="font-bold text-zinc-600 px-2">B</p>
            <div
              class="flex flex-row items-center space-x-2 text-lg font-semibold"
            >
              <input
                type="radio"
                :value="question.question.answer[1].answerId"
                :name="`aswer-radio-${index}`"
                v-model="userAswer.answerDtos[index].answer.answerId"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <p>{{ question.question.answer[1].contentAnswer }}</p>
            </div>
          </div>
          <div class="flex flex-col w-full">
            <p class="font-bold text-zinc-600 px-2">C</p>
            <div
              class="flex flex-row items-center space-x-2 text-lg font-semibold"
            >
              <input
                type="radio"
                :value="question.question.answer[2].answerId"
                :name="`aswer-radio-${index}`"
                v-model="userAswer.answerDtos[index].answer.answerId"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <p>{{ question.question.answer[2].contentAnswer }}</p>
            </div>
          </div>
          <div class="flex flex-col w-full">
            <p class="font-bold text-zinc-600 px-2">D</p>
            <div
              class="flex flex-row items-center space-x-2 text-lg font-semibold"
            >
              <input
                type="radio"
                :value="question.question.answer[3].answerId"
                :name="`aswer-radio-${index}`"
                v-model="userAswer.answerDtos[index].answer.answerId"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <p>{{ question.question.answer[3].contentAnswer }}</p>
            </div>
          </div>
        </div>
      </div>
      <button
        type="submit"
        class="bg-lightblue w-28 py-2 mx-auto rounded-xl text-white"
      >
        Submit
      </button>
    </form>
    <div
      class="flex flex-col w-fit fixed top-10 right-20 rounded-xl overflow-hidden shadow-xl bg-gray-100"
    >
      <p class="w-full text-3xl font-bold bg-gray-300 text-center py-2">
        Time:<span v-if="second > 300" class="ml-4 text-green-500">{{
          timeCountDown
        }}</span>
        <span v-else-if="second > 60" class="ml-4 text-yellow-500">{{
          timeCountDown
        }}</span>
        <span v-else="second > 60" class="ml-4 text-red-600">{{
          timeCountDown
        }}</span>
      </p>
      <div class="grid grid-cols-5 gap-5 mt-5 py-5 px-5">
        <div
          class="bg-white rounded-md w-10 h-10 flex flex-col items-center justify-center font-semibold"
          v-for="(question, index) in userAswer.answerDtos.length"
          :key="`question-${index}`"
          @click="scrollToQuestion(index)"
        >
          <p
            v-if="userAswer.answerDtos[index].answer.answerId != ''"
            class="bg-gray-500 w-full h-full text-center rounded-xl"
          >
            {{ index + 1 }}
          </p>
          <p v-else class="rounded-xl">{{ index + 1 }}</p>
        </div>
      </div>
    </div>
  </div>
  <ModalConfirm />
</template>
