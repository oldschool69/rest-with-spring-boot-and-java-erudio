import './styles.css'

import logoImage from '../../assets/logo.svg'
import padlock from '../../assets/padlock.png'

export default function Login() {
    return (
        <div className='login-container'>
            <section className='form'>
                <img src={logoImage} alt="Carecao Logo"></img>
                <form>
                    <h1>Acess your account</h1>
                    <input placeholder='Username'></input>
                    <input type='password' placeholder='Password'></input>
                    <button className='button' type='submit'>Login</button>
                </form>
            </section>
            <img src={padlock} alt="Login"></img>
        </div>
    );
}