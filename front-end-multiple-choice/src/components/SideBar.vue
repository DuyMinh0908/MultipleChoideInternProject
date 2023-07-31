<template>
  <div class="relative w-1/6 z-50">
    <div class="fixed bg-white">
      <ul class="space-y-6 p-4 text-center flex flex-col">
        <!-- <router-link
          :to="{ name: 'Home' }"
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
        >
          <Icon name="plus" class="w-8 h-8" />
        </router-link> -->
        <router-link
          :to="{ name: 'Home' }"
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
          :class="route.name === 'Home' ? 'bg-slate-200' : ''"
        >
          <Icon name="home" class="w-8 h-8 mx-auto" />
          <p>Trang chủ</p>
        </router-link>

        <router-link
          :to="{ name: 'Course' }"
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
          :class="route.name === 'Course' ? 'bg-slate-200' : ''"
        >
          <Icon name="course" class="w-8 h-8 mx-auto" />
          <p>Khóa học</p>
        </router-link>
        <router-link
          :to="{ name: 'Exam' }"
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
          :class="route.name === 'Exam' ? 'bg-slate-200' : ''"
        >
          <Icon name="exam" class="w-8 h-8 mx-auto" />
          <p>Đề thi</p>
        </router-link>
        <router-link
          :to="{ name: 'Blog' }"
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
          :class="route.name === 'Blog' ? 'bg-slate-200' : ''"
        >
          <Icon name="blog" class="w-8 h-8 mx-auto" />
          <p>Blog</p>
        </router-link>
        <li
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
        >
          <Icon name="management" class="w-8 h-8 mx-auto" />
          <p>Quản lý</p>
        </li>
        <li
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
        >
          <Icon name="logout" class="w-8 h-8 mx-auto" />
          <p>Đăng xuất</p>
        </li>
      </ul>
    </div>
  </div>
</template>
<script lang="ts" setup>
import Icon from "../icons/ClientDashboard.vue";
import { useRoute } from "vue-router";

const route = useRoute();
</script>
