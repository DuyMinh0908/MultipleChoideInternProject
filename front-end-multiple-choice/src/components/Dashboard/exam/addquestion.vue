<template>
  <Navigation />
  <div class="w-2/3 mx-auto">
    <p class="text-xl font-semibold text-orange-500:">Selct Option</p>
    <div class="flex flex-row space-x-4">
      <button
        @click="automaticallyExam"
        class="px-5 py-4 bg-orange-500 rounded-xl"
      >
        Automatically Exam
      </button>
      <button
        class="px-5 py-4 bg-lightblue rounded-xl"
        @click="getAllQuestionBySubject()"
      >
        Manually Exam
      </button>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { useRoute, useRouter } from "vue-router";
import { ref, Ref, onBeforeMount } from "vue";
import { api } from "../../../services/http-common";
import { useAuthStore } from "../../../store/authStore";
import Naviagtion from "../../Navigation.vue";
import { useNotificationStore } from "../../../store/notificationStore";
const notificationStore = useNotificationStore();
const route = useRoute();
const router = useRouter();
const exam = ref();
const getExamById = async () => {
  const { data } = await api.get(`/course/exam/${route.params.id}`);
  exam.value = data;
};
const getAllQuestionBySubject = async () => {
  const { data } = await api.get(`/questions/subject/${route.params.subject}`);
  console.log(data);
};
const automaticallyExam = async () => {
  try {
    const data = await api.post(
      `/exam/create-test/num=${exam.value.numQuestion}/subject=${route.params.subject}/exam-id=${exam.value.examId}`
    );
    notificationStore.openSuccess("Create success");
    router.push({ name: "Dashboard.Exam.List" });
  } catch (e) {
    notificationStore.openError(
      "The thread bank is not enough, please create it manually"
    );
  }
};
onBeforeMount(() => {
  getExamById();
});
</script>
