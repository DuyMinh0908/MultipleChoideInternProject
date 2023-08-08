<script lang="ts" setup>
import { Blog } from "../../model/blog";
interface Props {
  blog: Blog;
}
const prop = defineProps<Props>();
</script>
<template>
  <div class="flex flex-row bg-white rounded-xl border p-5 shadow-lg w-3/4">
    <div class="flex flex-col w-4/5 space-y-4">
      <div class="flex flex-row items-center space-x-4">
        <img
          class="w-10 h-10 rounded-full"
          src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80"
        />
        <p class="capitalize font-semibold hover:underline">
          {{ prop.blog.fullName }}
        </p>
      </div>
      <router-link
        :to="{ name: 'Blogs.Detail', params: { id: prop.blog.blogId } }"
        class="font-bold text-2xl hover:underline"
      >
        {{ prop.blog.titleBlog }}
      </router-link>
      <p class="line-clamp-2 w-4/5">
        {{ prop.blog.contentBlog }}
      </p>
    </div>
    <img
      class="rounded-xl w-2/5"
      src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdo8Fspior6AcTdonMdRm4Y2UQZpK8QrQoNWuCXIRB&s"
    />
  </div>
</template>
