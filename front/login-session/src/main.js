import { createApp } from "vue";
import { createPinia } from "pinia";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";
import "bootstrap-icons/font/bootstrap-icons.css";

import App from "./App.vue";
import router from "./router";

const app = createApp(App);

app.use(createPinia().use(piniaPluginPersistedstate));
app.use(router);

app.mount("#app");
