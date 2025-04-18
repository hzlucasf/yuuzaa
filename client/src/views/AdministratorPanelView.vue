<script lang="ts" setup>
import Button from '@/components/Button.vue'

import BackIcon from '@/assets/icons/back-icon.svg'

import { useRouter } from 'vue-router'

import View from '@/components/View.vue'

import UserCard from '@/components/UserCard.vue'

import { ref } from 'vue'

import { User } from '@/types/User'

import axios from 'axios'

import { onMounted } from 'vue'

const router = useRouter()

const back = () => router.back()

const users = ref<User[]>([])

onMounted(async () => {
    try {
        const jwt = localStorage.getItem('jwt')

        if (!jwt) {
            return
        }

        const response = await axios.get('http://localhost:8080/users', { headers: { Authorization: `Bearer ${jwt}` } })

        users.value = response.data.map((u: any) => new User(u.id, u.username, u.role))
    } catch (err) {
        console.error(err)

        localStorage.removeItem('jwt')
    }
})
</script>

<template>
    <Button :icon="BackIcon" :title="'Back'" @click="back" />

    <View>
        <UserCard :id="u.id" :username="u.username" :role="u.role" v-for="u in users" />
    </View>
</template>


<style scoped>
.view {
    flex-direction: column;
}

button {
    position: fixed;
}
</style>
