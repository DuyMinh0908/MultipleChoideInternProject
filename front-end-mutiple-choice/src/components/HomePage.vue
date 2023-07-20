<script lang="ts" setup>
import { useCount } from "../store/countStore";
import Logincomponent from "../components/Login.vue";
const counterStore = useCount();
</script>
<template>
  <h1 class="text-blue-500">Hello guy, this is page home</h1>
  <h1>{{ counterStore.getCount }}</h1>
  <button @click="counterStore.setCount">Click me</button>
  <Logincomponent />
</template>
