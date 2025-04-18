<script lang="ts" setup>
defineProps<{ id: string, username: string, role: string }>()

import AdministratorIcon from '@/assets/icons/administrator-icon.svg'

import UserIcon from '@/assets/icons/user-icon.svg'
</script>

<template>
    <div class="component">
        <img :src="role == 'ADMINISTRATOR' ? AdministratorIcon : UserIcon" />

        <h2>{{ username }}</h2>
    </div>
</template>

<style scoped>
.component {
    align-items: center;

    background-color: #fafafa;

    border-radius: 5px;

    box-sizing: border-box;

    display: flex;

    justify-content: space-between;

    flex-direction: row;

    margin-bottom: 16px;

    min-height: 128px;

    padding: 32px;

    transition: background-color 0.25s ease-in-out;

    width: 80%;
}

.component:hover {
    background-color: #f5f5f5;
}

h2 {
    color: #a3a3a3;

    font-family: 'Noto Sans';
}
</style>
