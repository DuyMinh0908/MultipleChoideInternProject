<script lang="ts" setup>
import { Course } from "../../model/course";
import { useRoute } from "vue-router";
interface Props {
  course: Course;
}
const prop = defineProps<Props>();
</script>
<template>
  <div class="group">
    <div
      class="bg-cover overflow-hidden bg-center bg-no-repeat h-60 rounded-xl relative flex items-center justify-center"
      :style="`background-image: url('${prop.course.img}');`"
    >
      <div
        class="group-hover:block hidden absolute bg-gray-500 opacity-50 w-full h-full"
      ></div>
      <router-link
        :to="{ name: 'Courses.Detail' }"
        class="bg-white rounded-xl absolute hidden group-hover:block py-2 px-4 cursor-pointer"
      >
        Xem khóa học
      </router-link>
    </div>
  </div>
</template>
