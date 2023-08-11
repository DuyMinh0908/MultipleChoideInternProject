import { createApp } from "vue";
import { createPinia } from "pinia";
import {} from "pinia";
import router from "./router/index";
import "./style.css";
import DropdownMenu from "v-dropdown-menu";
import App from "./App.vue";
import CKEditor from "@ckeditor/ckeditor5-vue";
import { createVfm } from "vue-final-modal";
import globalComponents from "./global-components";
import "vue-final-modal/style.css";
import VueTimepicker from "vue3-timepicker";

import "vue3-timepicker/dist/VueTimepicker.css";
const vfm = createVfm();
const app = createApp(App)
  .use(DropdownMenu)
  .use(vfm)
  .use(CKEditor)
  .use(router)
  .use(createPinia());
globalComponents(app);
app.mount("#app");
