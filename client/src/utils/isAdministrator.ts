import { jwtDecode } from "jwt-decode"

const isAdministrator = () => {
    try {
        const jwt = localStorage.getItem('jwt')

        if (!jwt) {
            return false
        }

        const decodedJwt: any = jwtDecode(jwt)

        if (decodedJwt.roles) {
            return decodedJwt.roles[0] == 'ROLE_ADMINISTRATOR'
        }

        return false
    } catch (err) {
        console.error(err)

        return false
    }
}

export default isAdministrator
