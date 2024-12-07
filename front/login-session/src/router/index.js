import { createRouter, createWebHistory } from "vue-router";
import LoginView from "@/views/LoginView.vue";
import SignupView from "@/views/SignupView.vue";
import MainView from "../views/MainView.vue";
import InfoView from "../views/InfoView.vue";
import { useUserStore } from "@/stores/user";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // 첫 화면은 로그인 화면으로 redirect
    {
      path: "/",
      redirect: "/login",
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
    {
      path: "/signup",
      name: "signup",
      component: SignupView,
    },
    {
      path: "/main",
      name: "main",
      component: MainView,
      meta: { requiresAuth: true },
    },
    {
      path: "/info",
      name: "info",
      component: InfoView,
    },
    {
      path: "/board",
      name: "board",
      component: () => import("../views/BoardView.vue"),
    },

    /*컴포넌트 로딩 방법 1. component: InfoView,
    앱이 처음 로드될 때(앱시작시) 모든 라우트에 대해 컴포넌트들이 한꺼번에 바로 메모리에 로드됨.
    이후에는 계속 재사용

    장점 : 빠르게 컴포넌트를 사용할 수 있다.
    단점 : 프로젝트 크기가 커질수록 앱 초기 로드 시간이 길어질 수 있다.
    */

    /*컴포넌트 로딩 방법 2. component: () => import("../views/BoardView.vue")
    동적 임포트(lazy loading)
    앱시작시 컴포넌트를 바로 메모리에 로드하지 않고, 해당 라우트를 처음 방문할 때 컴포넌트를 로드함.
    재방문 시 재사용

    장점 : 필요한 경우에만 컴포넌트를 로드하기 때문에 앱 초기 로드 속도가 빨라지고,
    사용자가 필요로 하지 않는 자원은 로드되지 않아 성능 최적화가 가능.
    단점 : 컴포넌트를 처음 로드할 때 시간이 조금 더 걸릴 수 있다.
    */

    // => 대규모 어플리케이션에서는 지연로드가 적합
  ],
});

// navigation guard
router.beforeEach((to, from, next) => {
  const userStore = useUserStore();
  const isAuthenticated = !!userStore.loginUser.loginId; // 로그인 여부 확인

  if (to.meta.requiresAuth && !isAuthenticated) {
    // 인증이 필요한 페이지인데 로그인이 안 되어 있으면
    next({ name: "login" });
  } else if (to.name === "login" && isAuthenticated) {
    // 로그인 상태에서 로그인 페이지로 접근하면 메인으로 리다이렉트
    next({ name: "main" });
  } else {
    next(); // 그 외에는 정상 진행
  }
});

export default router;
