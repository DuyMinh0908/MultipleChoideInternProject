import { createApp } from "vue";
import { createPinia } from "pinia";
import {} from "pinia";
import router from "./router/index";
import "./style.css";

import App from "./App.vue";

const app = createApp(App).use(router).use(createPinia());
app.mount("#app");
