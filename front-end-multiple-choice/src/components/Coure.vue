<template>
  <Navigation />
  <SideBar />
  <div></div>
  <div class="flex flex-col w-5/6 mx-auto">
    <h3 class="inline font-bold text-2xl">Khóa học</h3>
    <p>
      Các khóa học được thiết kế phù hợp cho cả người mới, nhiều khóa học miễn
      phí, chất lượng, nội dung dễ hiểu.
    </p>
    <div class="flex flex-col w-full mt-10 mx-auto space-y-4">
      <p class="font-semibold text-xl relative w-52 h-10 flex flex-col justify-end">Khóa học nổi bật
        <div class="absolute top-0 right-0  text-sm text-white font-semibold bg-blue-500 px-2 rounded-md">
          Mới
        </div>
      </p>
      <div class="grid grid-cols-3 gap-6">
        <Item v-for="course in popularCourses" :key="course.courseId" :course="course" />
        
      </div>
     
    </div>
    <div class="flex flex-col w-full mt-10 mx-auto space-y-4">
      <p class="font-semibold text-xl relative w-52 h-10 flex flex-col justify-end">Khóa học miễn phí    
      </p>
      <div class="grid grid-cols-3 gap-6">
        <Item v-for="course in allCourses " :key="course.id" :course="course" />
      </div>
     
    </div>
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../components/Navigation.vue";
import Item from "../components/Course/Item.vue";
import SideBar from "../components/SideBar.vue";
import { Course } from "../model/course";
import { api } from "../services/http-common";
import {ref, Ref, onBeforeMount} from"vue"
 
 
const popularCourses : Ref<Array<Course>> = ref([])
const allCourses :  Ref<Array<Course>> = ref([])
const getCoursePopular = async ()=>{
 try{
    const data = await api.get('/courses/popular-courses') ;
    console.log(data.data)
    popularCourses.value = data.data
    // console.log(typeof courses.value)
 }
 catch(e){
  console.error(e)
 }
}
const getAllCourses = async ()=>{
 try{
    const data = await api.get('/courses') ;
    console.log(data.data)
    allCourses.value = data.data
    
 }
 catch(e){
  console.error(e)
 }
}
onBeforeMount(() => {
  getCoursePopular()
  getAllCourses()
})
</script>
