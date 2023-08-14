<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-col w-5/6 pt-10 container space-y-4 text-start">
    <h1 class="font-semibold text-3xl">{{ blog.titleBlog }}</h1>
    <div class="flex flex-row items-center space-x-4">
      <img
        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmBjkH1mY9ZkjRNE8WqgATE9VFKoPDO2dNEA&usqp=CAU"
        class="w-16 h-16 rounded-full"
      />
      <p class="font-semibold">{{ blog.fullName }}</p>
    </div>
    <span v-html="blog.contentBlog"></span>
  </div>
  <div></div>
</template>
<script lang="ts" setup>
import Navigation from "../Navigation.vue";
import { useRoute } from "vue-router";
import SideBar from "../SideBar.vue";
import { Blog } from "../../model/blog";
import { ref, Ref, onBeforeMount } from "vue";
import { api } from "../../services/http-common";
const route = useRoute();
const blog: Ref<Blog> = ref({});
const getBlogDetail = async () => {
  try {
    const { data } = await api.get(`/blogs/${route.params.id}`);
    blog.value = data;
  } catch (e) {
    console.error(e);
  }
};
onBeforeMount(async () => {
  await getBlogDetail();
});
</script>
