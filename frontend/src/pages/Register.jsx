import React from 'react'
import {Link} from 'react-router-dom'

const Register = () => {
  return (
    <div  className='auth'>
        <h1 className='auth-heading'>Регистрация</h1>
        <form className="auth-form">
            <input required className="auth-input" type="text" placeholder='Имя'/>
            <input required className="auth-input" type="text" placeholder='Фамилия'/>
            <input required className="auth-input" type="email" placeholder='Почта'/>
            <input required className="auth-input" type="text" placeholder='Имя пользователя'/>
            <input  required className="auth-input" id="password1" type="password" placeholder='Пароль'/>
            <input  required className="auth-input" id="password2" type="password" placeholder='Повторите пароль'/>
            <span className='auth-error'>О нет! Пароли не совпадают</span>
            <span className='auth-error'>Сожалеем! Пользователь с данной почтой уже существует</span>
            <span className='auth-error'>Сожалеем! Имя пользователя уже занято</span>
            <span>Уже зарегистрированы? <Link to="/login">Войти</Link></span>
            <button className='auth-btn'>Зарегистрироваться</button>
        </form>
    </div>
  )
}

export default Register