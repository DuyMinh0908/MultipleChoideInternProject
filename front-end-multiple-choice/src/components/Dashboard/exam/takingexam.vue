<template>
  <div class="fixed top-20 right-0 w-fit px-5 h-20 bg-slate-300">
    <p class="text-red-600">Thoi gian con lai:{{ timeCountDown }}</p>
  </div>
  <form @submit.prevent="submitExam()">
    <div
      v-for="(question, index) in allQuestions"
      :key="question.question.questionId"
      class="rounded-xl border py-5 border-gray-200 mb-4 w-2/3 flex flex-col mx-auto"
    >
      <div class="grid grid-cols-2 gap-6 px-5 mt-4 w-full">
        <div class="flex flex-col w-full col-span-2 text-xl font-semibold">
          <p class="font-bold text-zinc-600 px-2">Câu hỏi {{ index + 1 }}:</p>
          <p class="text-red ml-4">
            {{ question.question.contentQuestion }}
          </p>
        </div>

        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">Đáp án A</p>
          <div
            class="flex flex-row items-center space-x-2 text-lg font-semibold"
          >
            <input
              type="radio"
              :value="question.question.answer[0].answerId"
              :name="`aswer-radio-${index}`"
              v-model="userAswer[index].answer.answerId"
              class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
            />
            <p>{{ question.question.answer[0].contentAnswer }}</p>
          </div>
        </div>
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">Đáp án B</p>
          <div
            class="flex flex-row items-center space-x-2 text-lg font-semibold"
          >
            <input
              type="radio"
              :value="question.question.answer[1].answerId"
              :name="`aswer-radio-${index}`"
              v-model="userAswer[index].answer.answerId"
              class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
            />
            <p>{{ question.question.answer[1].contentAnswer }}</p>
          </div>
        </div>
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">Đáp án C</p>
          <div
            class="flex flex-row items-center space-x-2 text-lg font-semibold"
          >
            <input
              type="radio"
              :value="question.question.answer[2].answerId"
              :name="`aswer-radio-${index}`"
              v-model="userAswer[index].answer.answerId"
              class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
            />
            <p>{{ question.question.answer[2].contentAnswer }}</p>
          </div>
        </div>
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">Đáp án D</p>
          <div
            class="flex flex-row items-center space-x-2 text-lg font-semibold"
          >
            <input
              type="radio"
              :value="question.question.answer[3].answerId"
              :name="`aswer-radio-${index}`"
              v-model="userAswer[index].answer.answerId"
              class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
            />
            <p>{{ question.question.answer[3].contentAnswer }}</p>
          </div>
        </div>
      </div>
    </div>
    <button type="submit">Click me</button>
  </form>
  <Pagination
    @get-list="getList"
    :last-page="response.last_page"
    :current-page="response.current_page"
  />
</template>
<script lang="ts" setup>
import { useRoute } from "vue-router";
import { ref, Ref, onBeforeMount, onMounted, watch } from "vue";
import { api } from "../../../services/http-common";
import Pagination from "../../Pagination.vue";
const route = useRoute();
const exam = ref();
const second = ref();
const timeCountDown = ref();
const allQuestions = ref();
const userAswer = ref([
  {
    answer: {
      answerId: "",
    },
    user: {
      userId: 1,
    },
    status: "null",
  },
]);
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
    console.log(userAswer.value);
    const data = await api.post("result-exam/create", userAswer.value);

    console.log(data);
  } catch (e) {
    console.error(e);
  }
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
      userAswer.value.push({
        answer: {
          answerId: "",
        },
        user: {
          userId: 4,
        },
        status: "null",
      });
    });
    userAswer.value.splice(0, 1);
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
    console.log(data);
    allQuestions.value = data.data.content;
    response.value.current_page = data.data.number;
    response.value.last_page = data.data.totalPages - 1;
  } catch (e) {}
};

watch(second, () => {
  timeCountDown.value = getTime();
  if (second.value === 0) {
    console.log("dasdas");
  }
});
onBeforeMount(async () => {
  await getExamByIdofCourse();
  await getAllQuestions();
});
onMounted(async () => {
  await getList();
  window.onpopstate = function (event) {
    alert("He thong se tu dong nop bai");
  };
});
</script>
