<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-col w-5/6 mx-auto">
    <h3 class="inline font-bold text-2xl">Bài viết nổi bật</h3>
    <p>
      Tổng hợp các bài viết chia sẻ về kinh nghiệm tự học lập trình online và
      các kỹ thuật lập trình web.
    </p>
    <div class="flex flex-col w-full mt-10 mx-auto space-y-4">
      <Item v-for="blog in allBlogs" :key="blog.blogId" :blog="blog" />
    </div>
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../components/Navigation.vue";
import Item from "../components/Blog/Item.vue";
import SideBar from "../components/SideBar.vue";
import { Blog } from "../model/blog";
import { api } from "../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";

const allBlogs: Ref<Array<Blog>> = ref([]);

const getAllBlogs = async () => {
  try {
    const data = await api.get("/blogs");
    allBlogs.value = data.data;
  } catch (e) {
    console.error(e);
  }
};
onBeforeMount(() => {
  getAllBlogs();
});
</script>
