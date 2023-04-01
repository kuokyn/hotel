import React from 'react'
import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
      <div className="navbar-container">
        <div className="navbar">
            <div className="links">
            <Link className="nav-link" to="/">Главная</Link>
            <Link className="nav-link" to="/?cat=food">Еда</Link>
            <Link className="nav-link" to="/?cat=movies">Фильмы</Link>
            <Link className="nav-link" to="/?cat=design">Дизайн</Link>
            <Link className="nav-link" to="/?cat=technology">Технологии</Link>
            <Link className="nav-link" to="/login">Войти</Link>
            <span className="nav-link">John</span>
            <span className="nav-link">Logout</span>
            <Link className=' nav-link nav-link-write' to="/write">Написать</Link>
          </div>
        </div>
      </div>
  )
}

export default Navbar