<script lang="ts" setup>
defineProps<{ icon: string, title: string }>()

const emit = defineEmits(['click'])

const onClick = () => emit('click')
</script>

<template>
    <button class="button" type="button" :title="title" @click="onClick">
        <img :src="icon" />
    </button>
</template>

<style scoped>
.button {
    background-color: #fafafa;

    border: none;

    border-radius: 5px;

    cursor: pointer;

    margin: 8px;

    outline: none;

    padding: 16px;

    transition: background-color 0.25s ease-in-out;
}

.button:hover {
    background-color: #f5f5f5;
}
</style>
