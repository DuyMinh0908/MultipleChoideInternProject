<script lang="ts" setup>
import "vue3-carousel/dist/carousel.css";
import { Carousel, Slide } from "vue3-carousel";

interface Course {
  id: Number;
  name: String;
  image?: String;
}
const items: Array<Course> = [
  { id: 1, name: "React", image: "../src/assets/images/banner-1.png" },
  { id: 2, name: "Java", image: "../src/assets/images/banner-2.png" },
  { id: 3, name: "C++", image: "../src/assets/images/banner-3.png" },
];
</script>

<template>
  <div>
    <Carousel :transition="500">
      <Slide v-for="item in items" :key="item.id">
        <div
          class="w-5/6 rounded-xl overflow-hidden flex flex-row justify-between bg-gradient-to-r from-indigo-500 from-10% via-sky-500 via-30% to-emerald-500 to-90%"
        >
          <div class="flex flex-col space-y-4 p-10 text-start text-white">
            <p class="text-3xl font-bold">{{ item.name }}</p>
            <p class="text-md font-semibold">
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
            <img :src="item.image" alt="img-banner" />
          </div>
        </div>
      </Slide>
    </Carousel>
  </div>
</template>
