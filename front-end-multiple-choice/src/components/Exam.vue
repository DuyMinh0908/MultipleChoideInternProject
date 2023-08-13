<script lang="ts" setup>
import Navigation from "../components/Navigation.vue";
import SideBar from "../components/SideBar.vue";
import { api } from "../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";
import Item from "../components/Exam/Item.vue";
const listExamInComing = ref();
const getInComingExam = async () => {
  const { data } = await api.get("user/4/incoming-exams?page=0&size=5");
  listExamInComing.value = data.content;
};

onBeforeMount(() => {
  getInComingExam();
});
</script>

<template>
  <Navigation />
  <SideBar />
  <div class="w-5/6 pt-10 container space-y-4 text-start grid grid-col-3">
    <template v-for="examInCominge in listExamInComing">
      <div class="flex flex-col rounded-xl shadow-xl bg-slate-200">
        <p>Số lượng câu hỏi:{{ examInCominge.numQuestion }}</p>
        <p>Thời gian:{{ examInCominge.duration }}</p>

        <router-link
          :to="{
            name: 'Dashboard.Exam.TakeExam',
            params: { id: examInCominge.examId },
          }"
          class="bg-lightblue text-white font-see.mibold px-3 py-2 w-32 rounded-xl text-center"
        >
          Thi ngay</router-link
        >
      </div>
    </template>
  </div>
</template>
