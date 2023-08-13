<template>
  <Navigation />
  <SideBarDashboard />
  <div class="w-4/6 ml-96 flex flex-col items-center">
    <template v-for="exam in listExams" :key="exam.examId">
      <router-link
        :to="{ name: 'Dashboard.Exam.TakeExam', params: { id: exam.examId } }"
      >
        {{ exam.numQuestion }}</router-link
      >
    </template>
  </div>
</template>
<script lang="ts" setup>
import { ref, Ref, onBeforeMount } from "vue";
import { api } from "../../../services/http-common";
import SideBarDashboard from "../SideBarDashboard.vue";
import Navigation from "../../Navigation.vue";
const listExams = ref();
const getListExams = async () => {
  const { data } = await api.get(`/course=4/exams`);
  listExams.value = data;
};
onBeforeMount(() => {
  getListExams();
});
</script>
