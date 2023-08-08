<template>
  <Navigation />
  <div id="visa">
    <h1>Vue Visa Application</h1>
    <form>
      <label for="first name">First Name:</label>
      <input type="text" required />
      <br />
      <label for="last name">Last Name:</label>
      <input type="text" required />
      <br />
      <label for="country">Nationality:</label>
      <input type="text" required />
      <br />
      <label for="passport number">Passport Number:</label>
      <input type="text" required />

      <label for="duration">Duration of stay:</label>
      <input type="text" required />
      <br /><br />
      <button @click="addChapter">Add another previous visa</button>
      <br />
      <div class="previous" v-for="(chapter, index) in chapters" :key="index">
        <span @click="deleteChapter(index + 1)">x</span>
        <label for="duration">Previous Visa:</label>
        <input type="text" required />
        <label for="duration">Year of expiration:</label>
        <input type="text" required />
      </div>
    </form>
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../../Navigation.vue";
import { ref, Ref } from "vue";
const chapters = ref([]);

const addChapter = () => {
  chapters.value.push({
    previous: "",
    expiration: "",
  });
};
function deleteChapter(counter) {
  chapters.value.splice(counter, 1);
}
</script>
