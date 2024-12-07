<template>
  <div class="container mt-5">
    <form
      class="p-4 border rounded shadow-sm bg-light"
      @submit.prevent="signup"
    >
      <fieldset>
        <legend class="text-center mb-4">Session Signup</legend>

        <div class="mb-3">
          <label for="loginId" class="form-label">Login ID</label>
          <input
            type="text"
            id="loginId"
            class="form-control"
            v-model.trim="data.loginId"
            placeholder="Enter your login ID"
          />
        </div>

        <div class="mb-3">
          <label for="loginId" class="form-label">Email</label>
          <input
            type="email"
            id="email"
            class="form-control"
            v-model.trim="data.email"
            placeholder="Enter your email"
          />
        </div>

        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <input
            type="password"
            id="password"
            class="form-control"
            v-model.trim="data.password"
            aria-describedby="PwdHelp"
            placeholder="Enter your password"
          />
          <small id="PwdHelp" class="form-text text-muted"
            >Password should be at least 8 characters long.</small
          >
        </div>

        <div class="text-center">
          <button type="submit" class="btn btn-primary w-100">Signup</button>
        </div>
      </fieldset>
    </form>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "../stores/user";
const userStore = useUserStore();
const router = useRouter();

const data = reactive({
  // ref쓰면 안되고 reactive쓰면 되네..(reactive는 value X)
  loginId: "",
  email: "",
  password: "",
});

// 회원가입
const signup = function () {
  console.log("data", data);
  userStore.signup(data);
  resetForm();
  router.push({ name: "login" });
};

const resetForm = function () {
  data.loginId = "";
  data.email = "";
  data.password = "";
};
</script>

<style scoped>
form {
  max-width: 400px;
  margin: 0 auto;
}
fieldset {
  padding: 20px;
  border-radius: 5px;
}
legend {
  font-size: 1.5rem;
  font-weight: bold;
  color: #333;
}
input::placeholder {
  color: #aaa;
}
button {
  font-size: 1rem;
  padding: 10px;
}
</style>
