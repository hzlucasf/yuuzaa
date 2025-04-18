<script lang="ts" setup>
import Button from '@/components/Button.vue'

import BackIcon from '@/assets/icons/back-icon.svg'

import { useRouter } from 'vue-router'

import View from '@/components/View.vue'

import SignInForm from '@/components/SignInForm.vue'

const router = useRouter()

const back = () => router.back()
</script>

<template>
    <Button :icon="BackIcon" :title="'Back'" @click="back" />

    <View>
        <SignInForm />
    </View>
</template>

<style scoped>
button {
    position: fixed;
}
</style>
