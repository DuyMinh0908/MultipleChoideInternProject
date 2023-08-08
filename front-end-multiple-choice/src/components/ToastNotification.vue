<template>
  <Notification refKey="successNotification" class="flex flex-row w-60">
    <Icon
      name="check"
      v-if="notificationStore.type === 'success'"
      class="text-green-600"
    />
    <Icon name="uncheck" v-else class="text-red-600" />
    <div class="ml-4 mr-4">
      <div
        v-if="notificationStore.type === 'success'"
        class="font-medium text-green-600"
      >
        Success!
      </div>
      <div v-else class="font-medium text-danger">Error!</div>
      <div
        v-if="notificationStore.show && notificationStore.message"
        class="text-slate-500 mt-1"
      >
        {{ notificationStore.message }}
      </div>
    </div>
  </Notification>
</template>

<script setup lang="ts">
import { provide, ref, watch } from "vue";
import { useNotificationStore } from "../store/notificationStore";
import { useRoute } from "vue-router";
import Icon from "../icons/ClientDashboard.vue";
const notificationStore = useNotificationStore();
const successNotification = ref();
const route = useRoute();

provide("bind[successNotification]", (el: any) => {
  successNotification.value = el;
});

watch(
  notificationStore,
  (state) => {
    if (state.show && state.message !== "You missed a request chat request.") {
      successNotification.value.showToast();
      setTimeout(() => {
        successNotification.value.hideToast();
        notificationStore.clearMessage();
      }, state.timeout);
    }
  },
  { deep: true }
);
</script>
