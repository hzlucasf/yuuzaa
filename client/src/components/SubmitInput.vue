<script lang="ts" setup>
defineProps<{ value: string }>()
</script>

<template>
    <input class="submit-input" type="submit" :value />
</template>

<style scoped>
.submit-input {
    background-color: #2563eb;

    border: none;

    border-radius: 5px;

    color: #fafafa;

    cursor: pointer;

    font-family: 'Noto Sans';

    font-weight: 600;

    font-size: 1rem;

    min-height: 48px;

    min-width: 640px;

    transition: background-color 0.25s ease-in-out;
}

.submit-input:hover {
    background-color: #1d4ed8;
}
</style>
