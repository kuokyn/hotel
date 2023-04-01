import React , {useState} from 'react'
import {Link} from 'react-router-dom'

function Login() {
  const [phoneError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');

  const onSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);

    fetch("http://localhost:8080/login", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        phone: formData.get('phone'),
        password: formData.get('password'),
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        if(data.fieldErrors) {
          data.fieldErrors.forEach(fieldError => {
            if(fieldError.field === 'phone'){
              setEmailError(fieldError.message);
            }

            if(fieldError.field === 'password'){
              setPasswordError(fieldError.message);
            }
          });
        } else {
          alert("Success !!");
        }
      })
      .catch((err) => err);
  }

  const onEmailFocus = (e) => {
    e.preventDefault();
    setEmailError('');
  }

  const onPasswordFocus = (e) => {
    e.preventDefault();
    setPasswordError('');
  }
  return (
    <div className="auth">
        <h1 className="auth-heading">Вход</h1>
        <form className="auth-form" onSubmit={onSubmit}>
            <input onFocus={onEmailFocus} required className="auth-input" type="text" placeholder='Номер телефона'/>
            {
                        phoneError ? <span style={{ color: 'red', fontSize: '12px'}}>{phoneError}</span> : 'неправильно'
                      }
            <input onFocus={onPasswordFocus} required className="auth-input" type="password" placeholder='Пароль'/>
            {
                        passwordError ? <span style={{ color: 'red', fontSize: '12px'}}>{passwordError}</span> : 'неправильно'
                      }
            <button  className="auth-btn">Войти</button>
            <span className='auth-error'>Неправильное имя пользователя или пароль</span>
            <span>Нет аккаунта? <Link to="/register">Зарегистрируйся!</Link></span>
        </form>
    </div>
  )
}

export default Login