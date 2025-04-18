import { createRouter, createWebHistory, type NavigationGuardNext, type RouteLocationNormalized } from 'vue-router'

import HomeView from '@/views/HomeView.vue'

import SignInView from '@/views/SignInView.vue'

import SignUpView from '@/views/SignUpView.vue'

import isAuthenticated from '@/utils/isAuthenticated'

import AdministratorPanelView from '@/views/AdministratorPanelView.vue'

import isAdministrator from '@/utils/isAdministrator'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            name: 'Home',
            path: '/',
            component: HomeView
        },
        {
            name: 'Sign In',
            path: '/sign-in',
            component: SignInView,
            beforeEnter: (to: RouteLocationNormalized, from: RouteLocationNormalized, next: NavigationGuardNext) => {
                if (isAuthenticated()) {
                    next('/')
                } else {
                    next()
                }
            }
        },
        {
            name: 'Sign Up',
            path: '/sign-up',
            component: SignUpView,
            beforeEnter: (to: RouteLocationNormalized, from: RouteLocationNormalized, next: NavigationGuardNext) => {
                if (isAuthenticated()) {
                    next('/')
                } else {
                    next()
                }
            }
        },
        {
            name: 'Administrator Panel',
            path: '/administrator-panel',
            component: AdministratorPanelView,
            beforeEnter: (to: RouteLocationNormalized, from: RouteLocationNormalized, next: NavigationGuardNext) => {
                if (!isAuthenticated()) {
                    next('/')
                } else {
                    if (isAdministrator()) {
                        next()
                    } else {
                        next('/')
                    }
                }
            }
        }
    ],
})

export default router
