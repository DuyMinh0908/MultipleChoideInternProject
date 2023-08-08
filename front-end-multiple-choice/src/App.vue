<template>
  <router-view />
  <div class="fixed right-0 top-0"><ToastNotification /></div>
</template>
<script lang="ts" setup>
import ToastNotification from "../src/components/ToastNotification.vue";
</script>
