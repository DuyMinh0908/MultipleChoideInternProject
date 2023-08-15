<template>
  <div class="relative z-40 h-full">
    <div class="fixed w-fit h-full bg-gray-100 border-gray-400 shadow-xl py-10">
      <ul class="text-start flex flex-col text-md font-semibold">
        <router-link
          :to="{ name: 'Dashboard' }"
          class="flex flex-col hover:bg-slate-200 py-2 px-6 rounded-xl border-b"
          :class="route.name === 'Dashboard' ? 'bg-slate-400' : ''"
        >
          <p>DashBoard</p>
        </router-link>
        <dropdown-menu>
          <template #trigger>
            <div
              class="flex flex-row hover:bg-slate-200 py-2 px-6 border-b space-x-4 items-center"
            >
              <p>Courses Managerment</p>
              <Icon name="down" class="w-4 h-4" />
            </div>
          </template>

          <template #body>
            <router-link
              :to="{ name: 'Dashboard.Course.Index' }"
              class="flex flex-col space-y-2 border-b bg-gray-200 hover:bg-slate-200 py-2 px-6"
              :class="
                route.name === 'Dashboard.Course.Index' ? 'bg-slate-400' : ''
              "
            >
              All Course
            </router-link>
            <router-link
              :to="{ name: 'Dashboard.Course.Index' }"
              class="flex flex-col space-y-2 border-b bg-gray-100 hover:bg-slate-200 py-2 px-6"
              :class="
                route.name === 'Dashboard.Course.Index' ? 'bg-slate-400' : ''
              "
            >
              Danh sách khóa hỞc
            </router-link>
            <router-link
              :to="{ name: 'Dashboard.Course.Index' }"
              class="flex flex-col space-y-2 border-b bg-gray-100 hover:bg-slate-200 py-2 px-6"
              :class="
                route.name === 'Dashboard.Course.Index' ? 'bg-slate-400' : ''
              "
            >
              Danh sách khóa hỞc
            </router-link>
          </template>
        </dropdown-menu>
        <dropdown-menu>
          <template #trigger>
            <div
              class="flex flex-row hover:bg-slate-200 py-2 px-6 border-b space-x-4 items-center"
            >
              <p>Exam Managerment</p>
              <Icon class="w-4 h-4" name="down" />
            </div>
          </template>

          <template #body>
            <router-link
              :to="{ name: 'Exam' }"
              class="flex flex-col space-y-2 hover:bg-slate-200 py-2 px-6"
              :class="route.name === 'Exam' ? 'bg-slate-400' : ''"
            >
              <p>ĞỞ thi</p>
            </router-link>
            <router-link
              :to="{ name: 'Exam' }"
              class="flex flex-col space-y-2 hover:bg-slate-200 py-2 px-6"
              :class="route.name === 'Exam' ? 'bg-slate-400' : ''"
            >
              <p>Tạo đỞ thi</p>
            </router-link>
          </template>
        </dropdown-menu>
        <router-link
          :to="{ name: 'Dashboard.Blog.Index' }"
          class="flex flex-col space-y-2 hover:bg-slate-200 py-2 px-6"
          :class="route.name === 'Dashboard.Blog.Index' ? 'bg-slate-400' : ''"
        >
          <p>Blog</p>
        </router-link>
      </ul>
    </div>
  </div>
</template>
<script lang="ts" setup>
import Icon from "../../icons/ClientDashboard.vue";
import { useRoute } from "vue-router";

const route = useRoute();
</script>
