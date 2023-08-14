<template>
  <Navigation />
  <SideBarDashboard />
  <div class="w-full mx-auto flex flex-col items-center">
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
      <select
        v-model="courseId"
        class="bg-gray-50 border w-1/2 border-gray-300 text-gray-900 mb-6 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
      >
        <option v-for="course in allCourse" :value="course.courseId">
          {{ course.courseName }}
        </option>
      </select>
      <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead
          class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
        >
          <tr>
            <th scope="col" class="px-6 py-3">Exam id</th>
            <th scope="col" class="px-6 py-3">Num Question</th>
            <th scope="col" class="px-6 py-3">Duration</th>
            <th scope="col" class="px-6 py-3">Action</th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="bg-white border-b dark:bg-gray-900 dark:border-gray-700"
            v-for="exam in listExams"
            :key="exam.examId"
          >
            <th
              scope="row"
              class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
            >
              {{ exam.examId }}
            </th>
            <td class="px-6 py-4">{{ exam.numQuestion }}</td>
            <td class="px-6 py-4">{{ exam.duration }}</td>

            <td class="px-6 py-4">
              <button
                @click="deleteExamById(exam.examId)"
                href="#"
                class="font-medium text-red-600 hover:underline"
              >
                Delete
              </button>
              <route-link> Edit </route-link>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { ref, Ref, onBeforeMount, onMounted, watch } from "vue";
import { api } from "../../../services/http-common";
import SideBarDashboard from "../SideBarDashboard.vue";
import Navigation from "../../Navigation.vue";
const listExams = ref();
const allCourse = ref();
const courseId = ref(4);
const getListExams = async () => {
  const { data } = await api.get(`/course=${courseId.value}/exams`);
  listExams.value = data;
  console.log(listExams.value);
};
const deleteExamById = async (id) => {
  console.log(id);
  await api.delete(`/course/exam/delete/${id}`);
};
const getAllCourse = async () => {
  const { data } = await api.get("/courses");
  allCourse.value = data.content;
  console.log(allCourse.value);
};
watch(courseId, () => {
  getListExams();
});
onMounted(() => {
  getListExams();
});
onBeforeMount(() => {
  getListExams();
  getAllCourse();
});
</script>
