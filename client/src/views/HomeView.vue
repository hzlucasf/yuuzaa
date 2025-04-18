<script lang="ts" setup>
import Button from '@/components/Button.vue'

import isAuthenticated from '@/utils/isAuthenticated'

import SignInIcon from '@/assets/icons/sign-in-icon.svg'

import { useRouter } from 'vue-router'

import LogoutIcon from '@/assets/icons/logout-icon.svg'

import isAdministrator from '@/utils/isAdministrator'

import AdministratorPanelIcon from '@/assets/icons/administrator-panel-icon.svg'

import View from '@/components/View.vue'

import Title from '@/components/Title.vue'

const router = useRouter()

const redirectTo = (path: string) => router.push(path)

const logout = () => {
    localStorage.removeItem('jwt')

    window.location.reload()
}
</script>

<template>
    <div class="position-fixed">
        <Button v-if="!isAuthenticated()" :icon="SignInIcon" :title="'Sign In'" @click="redirectTo('/sign-in')" />
    </div>

    <div class="position-fixed">
        <Button v-if="isAuthenticated()" :icon="LogoutIcon" :title="'Logout'" @click="logout" />

        <Button v-if="isAdministrator()" :icon="AdministratorPanelIcon" :title="'Administrator Panel'" @click="redirectTo('/administrator-panel')" />
    </div>

    <View>
        <Title :content="'ユーザー'" />
    </View>
</template>

<style scoped>
.position-fixed {
    display: flex;

    flex-direction: column;

    position: fixed;
}
</style>
