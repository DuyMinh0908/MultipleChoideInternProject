<script lang="ts" setup>
import "vue3-carousel/dist/carousel.css";
import { Carousel, Slide } from "vue3-carousel";
import { Course } from "../model/course";
import { api } from "../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";
const popularCourses: Ref<Array<Course>> = ref([]);

const getCoursePopular = async () => {
  try {
    const data = await api.get("/courses/popular-courses");
    console.log(data.data);
    popularCourses.value = data.data;
    console.log(popularCourses.value);
  } catch (e) {
    console.error(e);
  }
};
onBeforeMount(() => {
  getCoursePopular();
});
</script>

<template>
  <div>
    <Carousel :transition="500">
      <Slide v-for="course in popularCourses" :key="course.courseId">
        <div
          class="w-5/6 rounded-xl overflow-hidden flex flex-row h-60 justify-between bg-gradient-to-r from-indigo-500 from-10% via-sky-500 via-30% to-emerald-500 to-90%"
        >
          <div class="flex flex-col space-y-4 p-10 text-start text-white">
            <p class="text-3xl font-bold">{{ course.subject }}</p>
            <p class="text-md font-semibold line-clamp-2">
              Để đạt được kết quả tốt trong mọi việc ta cần xác định mục tiêu rõ
              ràng cho việc đó. Học lập trình cũng không là ngoại lệ.
            </p>
            <button
              class="border-collapse border-white rounded-2xl border-2 w-40 text-md font-semibold hover:font-bold hover:bg-slate-500"
            >
              Tìm hiểu thêm
            </button>
          </div>
          <div class="opacity-100">
            <img :src="course.imageCourse" alt="img-banner" />
          </div>
        </div>
      </Slide>
    </Carousel>
  </div>
</template>
