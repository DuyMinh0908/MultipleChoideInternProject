<template>
  <div class="relative z-50">
    <div
      class="fixed bg-gray-100 w-1/6 border-r rounded-r-xl h-screen border-gray-400 shadow-xl"
    >
      <ul class="space-y-6 p-4 text-start flex flex-col text-xl font-semibold">
        <router-link
          :to="{ name: 'Dashboard' }"
          class="flex flex-col space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
          :class="route.name === 'Dashboard' ? 'bg-slate-400' : ''"
        >
          <p>DashBoard</p>
        </router-link>
        <dropdown-menu>
          <template
            class="flex flex-col space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
            #trigger
          >
            <p>Khóa học <Icon name="down" class="w-6 h-6" /></p>
          </template>

          <template #body>
            <router-link
              :to="{ name: 'Dashboard.Course.Index' }"
              class="flex flex-col space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
              :class="
                route.name === 'Dashboard.Course.Index' ? 'bg-slate-400' : ''
              "
            >
              Danh sách khóa học
            </router-link>
          </template>
        </dropdown-menu>
        <dropdown-menu>
          <template class="flex items-center flex-row" #trigger>
            <p>Đề thi</p>
            <Icon class="w-6 h-6" name="down" />
          </template>

          <template #body>
            <router-link
              :to="{ name: 'Exam' }"
              class="flex flex-col space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
              :class="route.name === 'Exam' ? 'bg-slate-400' : ''"
            >
              <p>Đề thi</p>
            </router-link>
            <router-link
              :to="{ name: 'Exam' }"
              class="flex flex-col space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
              :class="route.name === 'Exam' ? 'bg-slate-400' : ''"
            >
              <p>Tạo đề thi</p>
            </router-link>
          </template>
        </dropdown-menu>
        <router-link
          :to="{ name: 'Dashboard.Blog.Index' }"
          class="flex flex-col space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
          :class="route.name === 'Dashboard.Blog.Index' ? 'bg-slate-400' : ''"
        >
          <p>Blog</p>
        </router-link>

        <li
          class="flex flex-col space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
        >
          <p>Đăng xuất</p>
        </li>
      </ul>
    </div>
  </div>
</template>
<script lang="ts" setup>
import Icon from "../../icons/ClientDashboard.vue";
import { useRoute } from "vue-router";

const route = useRoute();
</script>
