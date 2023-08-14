<script lang="ts" setup>
import Navigation from "../../Navigation.vue";

import { api } from "../../../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";
import Icon from "../../../icons/ClientDashboard.vue";
import { ModalsContainer, useModal } from "vue-final-modal";
import ModalConfirm from "../../modals/ModalConfirm.vue";
import SideBarDasboard from "../SideBarDashboard.vue";
import { useNotificationStore } from "../../../store/notificationStore";
import { useRoute, useRouter } from "vue-router";
const notificationStore = useNotificationStore();
const route = useRoute();
const allQuestions = ref([]);
const subject: Ref<String> = ref("");
subject.value = String(route.params.subject);
const currentIdQuestion: Ref<Number> = ref(0);
const router = useRouter();
const { open, close } = useModal({
  component: ModalConfirm,
  attrs: {
    title: "Xác nhận!",

    async onConfirm() {
      try {
        await api.delete(`/questions/delete/${currentIdQuestion.value}`);
        await getAllQuestions();
        notificationStore.openSuccess("Xóa thành công");
      } catch (e) {
        notificationStore.openError("Xóa không thành công");
        console.error(e);
      }
      close();
    },
    onReject() {
      currentIdQuestion.value = 0;

      close();
    },
  },
  slots: {
    default: `<p class="text-center font-semibold text-xl">Bạn có chắc chắn muốn xóa</p>`,
  },
});
const getAllQuestions = async () => {
  const { data } = await api.get(`/questions/subject/${subject.value}`);
  allQuestions.value = data;
  console.log(allQuestions.value);
};
const deleteQuestionItem = async (id: Number) => {
  currentIdQuestion.value = id;
  console.log(currentIdQuestion.value);
  open();
};
onBeforeMount(() => {
  getAllQuestions();
});
</script>
<template>
  <Navigation />
  <SideBarDasboard />
  <div class="w-4/6 ml-96 flex flex-col items-center">
    <h1 class="text-3xl font-bold">List Question</h1>
    <div
      v-for="question in allQuestions"
      :key="question.questionId"
      class="rounded-xl border py-5 border-gray-200 mb-4 w-full"
    >
      <Icon
        @click="deleteQuestionItem(question.questionId)"
        name="delete"
        class="w-5 h-5"
      />
      <div class="grid grid-cols-2 gap-6 px-5 mt-4 w-full">
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">Câu hỏi</p>
          <p class="text-red">{{ question.contentQuestion }}</p>
        </div>
        <div
          class="flex flex-row items-center justify-items-center space-x-2 w-full"
        >
          <p class="font-bold text-zinc-600 px-2">Level:</p>
          <div class="flex items-center">
            <p>{{ question.level }}</p>
          </div>
        </div>
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">Đáp án A</p>
          <P>{{ question.answer[0].contentAnswer }}</P>
        </div>
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">Đáp án C</p>
          <P>{{ question.answer[1].contentAnswer }}</P>
        </div>
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">Đáp án B</p>
          <P>{{ question.answer[2].contentAnswer }}</P>
        </div>
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">Đáp án D</p>
          <P>{{ question.answer[2].contentAnswer }}</P>
        </div>
      </div>
    </div>
    <ModalsContainer />
  </div>
</template>
