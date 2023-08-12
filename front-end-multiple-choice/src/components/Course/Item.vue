<script lang="ts" setup>
import { Course } from "../../model/course";
interface Props {
  course: Course;
}
const prop = defineProps<Props>();
</script>
<template>
  <div class="flex flex-col space-y-2 rounded-xl shadow-xl">
    <div class="group">
      <div
        class="bg-cover overflow-hidden bg-center bg-no-repeat h-60 rounded-xl relative flex items-center justify-center"
        :style="`background-image: url('${prop.course.imageCourse}');`"
      >
        <div
          class="group-hover:block hidden absolute bg-gray-500 opacity-50 w-full h-full"
        ></div>
        <router-link
          :to="{
            name: 'Courses.Detail',
            params: { id: `${prop.course.courseId}` },
          }"
          class="bg-white rounded-xl absolute hidden group-hover:block py-2 px-4 cursor-pointer"
        >
          Xem khóa học
        </router-link>
      </div>
    </div>
    <p class="text-xl font-semibold">{{ course.courseName }}</p>
    <p class="text-gray-500">{{ course.numberStudent }}</p>
  </div>
</template>
