<script lang="ts" setup>
defineProps<{ message: string }>()

import Notification from './Notification.vue'

import ErrorIcon from '@/assets/icons/error-icon.svg'
</script>

<template>
    <Notification :icon="ErrorIcon" :message="message" :type="'error'" />
</template>
