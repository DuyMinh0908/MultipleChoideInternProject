<template>
  <Navigation />
  <div class="flex justify-center">
    <form
      @submit.prevent="saveQuestions()"
      class="w-3/5 px-20 rounded-md border-2 border-zinc-800 h-fit my-14"
    >
      <h1 class="flex uppercase justify-center font-medium text-2xl my-10">
        Tạo ngân hàng đề
      </h1>
      <div
        class="flex flex-col space-y-4 border border-gray-400 shadow-xl rounded-xl p-5"
        v-for="(formQuestion, index) in formQuestions"
        :key="formQuestion"
      >
        <Icon @click="removeQuestion(index)" name="delete" class="w-5 h-5" />

        <div class="grid grid-cols-2 gap-6 px-5 mt-4">
          <div class="flex flex-col w-full">
            <p class="font-bold text-zinc-600 px-2">
              Nhâp câu hỏi<span class="text-red-600 font-bold">*</span>
            </p>
            <input
              class="h-12 rounded-xl pl-2 border-2"
              v-model="formQuestion.contentQuestion"
              placeholder="Nhập mã khóa học"
            />
          </div>
          <div
            class="flex flex-row items-center justify-items-center space-x-2"
          >
            <p class="font-bold text-zinc-600 px-2">
              Level <span class="text-red-600 font-bold">*</span>
            </p>
            <div class="flex items-center">
              <input
                id="default-radio-1"
                type="radio"
                value="easy"
                v-model="formQuestion.level"
                name="default-radio"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <label
                for="default-radio-1"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >Dễ
              </label>
            </div>
            <div class="flex items-center">
              <input
                checked
                id="default-radio-2"
                type="radio"
                value="medium"
                v-model="formQuestion.level"
                name="default-radio"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <label
                for="default-radio-2"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >Vừa</label
              >
            </div>
            <div class="flex items-center">
              <input
                checked
                id="default-radio-2"
                type="radio"
                value="hard"
                v-model="formQuestion.level"
                name="default-radio"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <label
                for="default-radio-2"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >Khó</label
              >
            </div>
          </div>
          <div class="flex flex-col">
            <p class="font-bold text-zinc-600 px-2">
              Đáp án 1 <span class="text-red-600 font-bold">*</span>
            </p>
            <input
              class="h-12 rounded-xl w-full pl-2 border-2"
              name=""
              id=""
              v-model="formQuestion.answer[0].contentAnswer"
              placeholder="Nhập mã khóa học"
            />
          </div>
          <div class="flex flex-col">
            <p class="font-bold text-zinc-600 px-2">
              Đáp án 2 <span class="text-red-600 font-bold">*</span>
            </p>
            <input
              class="h-12 rounded-xl w-full pl-2 border-2"
              name=""
              v-model="formQuestion.answer[1].contentAnswer"
              id=""
              placeholder="Nhập mã khóa học"
            />
          </div>
          <div class="flex flex-col">
            <p class="font-bold text-zinc-600 px-2">
              Đáp án 3 <span class="text-red-600 font-bold">*</span>
            </p>
            <input
              class="h-12 rounded-xl w-full pl-2 border-2"
              name=""
              id=""
              placeholder="Nhập mã khóa học"
              v-model="formQuestion.answer[2].contentAnswer"
            />
          </div>
          <div class="flex flex-col">
            <p class="font-bold text-zinc-600 px-2">
              Đáp án 4 <span class="text-red-600 font-bold">*</span>
            </p>
            <input
              class="h-12 rounded-xl w-full pl-2 border-2"
              name=""
              id=""
              v-model="formQuestion.answer[3].contentAnswer"
              placeholder="Nhập mã khóa học"
            />
          </div>
        </div>
      </div>
      <div
        @click="addQuestion"
        class="rounded-xl px-3 mt-4 py-2 text-white bg-lightblue hover:bg-blue-600 w-40"
      >
        Thêm câu hỏi
      </div>
      <div class="flex-row gap-10 my-10 flex justify-center h-10">
        <button
          type="submit"
          class="rounded-md bg-green-300 w-20 hover:bg-green-600 font-bold text-white"
        >
          LƯU
        </button>
        <button
          class="rounded-md bg-zinc-400 w-20 hover:bg-red-600 font-bold text-white"
        >
          HỦY
        </button>
      </div>
    </form>
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../../Navigation.vue";
import { ref, Ref } from "vue";
import { api } from "../../../services/http-common";
import { useNotificationStore } from "../../../store/notificationStore";
import { useRouter, useRoute } from "vue-router";
import Icon from "../../../icons/ClientDashboard.vue";
const notificationStore = useNotificationStore();
const route = useRoute();
const router = useRouter();
const subject: Ref<String> = ref("");
subject.value = String(route.params.subject);

const formQuestions = ref([
  {
    contentQuestion: "",
    level: "",
    subject: subject.value,
    answer: [
      {
        contentAnswer: "",
      },
      {
        contentAnswer: "",
      },
      {
        contentAnswer: "",
      },
      {
        contentAnswer: "",
      },
    ],
  },
]);

const removeQuestion = (index: Number) => {
  formQuestions.value.splice(Number(index), 1);
};
const addQuestion = () => {
  formQuestions.value.push({
    contentQuestion: "",
    level: "",
    subject: subject.value,
    answer: [
      {
        contentAnswer: "",
      },
      {
        contentAnswer: "",
      },
      {
        contentAnswer: "",
      },
      {
        contentAnswer: "",
      },
    ],
  });
};
const saveQuestions = async () => {
  try {
    await api.post("/questions/create", formQuestions.value);
    notificationStore.openSuccess("Tạo câu hỏi thành công");
    await router.push(`/dashboard/subject/${subject.value}`);
  } catch (e) {
    console.error("Tạo câu hỏi thất bại");
  }
};
</script>
