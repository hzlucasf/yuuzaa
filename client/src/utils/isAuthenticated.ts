import { jwtDecode } from "jwt-decode"

const isAuthenticated = () => {
    try {
        const jwt = localStorage.getItem('jwt')

        if (!jwt) {
            return false
        }

        const { exp } = jwtDecode(jwt)

        if (!exp) {
            return false
        }

        if (exp * 1_000 <= Date.now()) {
            localStorage.removeItem('jwt')

            return false
        }

        return true
    } catch (err) {
        console.error(err)

        return false
    }
}

export default isAuthenticated
