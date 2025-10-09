import './styles.css';

import logoImage from '../../assets/logo.svg'
import { Link } from "react-router-dom";
import { useHistory } from 'react-router-dom';
import { useState, useEffect, use } from 'react';
import api from '../../services/api';
import { FiPower, FiEdit, FiTrash2 } from 'react-icons/fi';

export default function Books() {

  const [books, setBooks] = useState([]);
  const token = localStorage.getItem('accessToken');
  const userName = localStorage.getItem('username');
  const history = useHistory();

  useEffect(() => {
    api.get('/book', { headers: {
        'Authorization': `Bearer ${token}`,
    } }).then(response => {
        //console.log(response.data);
        setBooks(response.data._embedded.bookDTOList);
    }) 
  });

  return (
    <div className="book-container">
      <header>
        <img src={logoImage} alt="Carecao Logo" />
        <span>Welcome, <strong>{userName.toUpperCase()}</strong></span>
        <Link className="button" to="/book/new">Add New Book</Link>
        <button type="button">
            <FiPower size={18} color="#251FC5" />
        </button>
      </header>

      <h1>Registered Books</h1>
      <ul>
        {books.map(book => (
          <li key={book.id}>
            <strong>Title:</strong>
            <p>{book.title}</p>
            <strong>Author:</strong>
            <p>{book.author}</p>
            <strong>Price:</strong>
            <p>{Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'}).format(book.price)}</p>
            <strong>Release Date:</strong>
            <p>{new Date(book.launchDate).toLocaleDateString('pt-BR')}</p>
            <button type="button">
              <FiEdit size={20} color="#251FC5" />
            </button>
            <button type="button">
              <FiTrash2 size={20} color="#251FC5" />
            </button>
          </li>
        ))}
      </ul>  
    </div>
  );
}