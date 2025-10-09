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

  async function logout() {
    localStorage.clear();
    history.push('/');
  }

  async function editBook(id) {
    history.push(`/book/new/${id}`);
  }

  async function deleteBook(id) {
    try {
      await api.delete(`/book/${id}`, {
        headers: { 
          'Authorization': `Bearer ${token}`,
        }
      });
      setBooks(books.filter(book => book.id !== id));
    } catch (err) {
      alert('Error deleting book, try again.');
    }
  }

  useEffect(() => {
    api.get('/book', { headers: {
        'Authorization': `Bearer ${token}`,
    },
    params: {
      page: 1, 
      size: 4, 
      direction: 'asc'
    }
  }).then(response => {
        //console.log(response.data);
        setBooks(response.data._embedded.bookDTOList);
    }) 
  });

  return (
    <div className="book-container">
      <header>
        <img src={logoImage} alt="Carecao Logo" />
        <span>Welcome, <strong>{userName.toUpperCase()}</strong></span>
        <Link className="button" to="/book/new/0">Add New Book</Link>
        <button type="button" onClick={logout}>
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
            <button type="button" onClick={() => editBook(book.id)}>
              <FiEdit size={20} color="#251FC5" />
            </button>
            <button type="button" onClick={() => deleteBook(book.id)}>
              <FiTrash2 size={20} color="#251FC5" />
            </button>
          </li>
        ))}
      </ul>  
    </div>
  );
}