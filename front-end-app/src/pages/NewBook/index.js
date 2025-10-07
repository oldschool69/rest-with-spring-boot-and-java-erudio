import { Link } from 'react-router-dom';
import { FiArrowLeft } from 'react-icons/fi';
import { useHistory } from 'react-router-dom';
import { useState } from 'react';
import api from '../../services/api';

import './styles.css';

import logoImage from '../../assets/logo.svg'


export default function Books() {
    const history = useHistory();
    const [id, setId] = useState(null);
    const [title, setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [price, setPrice] = useState('');
    const [launchDate, setLaunchDate] = useState('');

    async function createNewBook(e){
        e.preventDefault();

        const token = localStorage.getItem('accessToken');
        const userName = localStorage.getItem('username');

        const data = {
            title,  
            author,
            price,
            launchDate
        };

        const headers = {
            'Authorization': `Bearer ${token}`,
        };

        try {
            await api.post('/book', data, { headers: headers });
            history.push('/books');
        } catch (err) {
            alert('Error creating new book, try again.');
        }
    }

    return (
        <div className="new-book-container">
            <div className="content">
                <section className='form'>
                    <img src={logoImage} alt="Carecao Logo"></img>
                    <h1>Add New Book</h1>
                    <p>Fill in the details below to add a new book to the collection.</p>
                    <Link className="back-link" to="/books">
                        <FiArrowLeft size={16} color="#251FC5" />
                        Home
                    </Link>
                </section>
                <form onSubmit={createNewBook}>
                    <input placeholder='Title' value={title} onChange={e => setTitle(e.target.value)}></input>
                    <input placeholder='Author' value={author} onChange={e => setAuthor(e.target.value)}></input>
                    <input type="date" value={launchDate} onChange={e => setLaunchDate(e.target.value)}></input>
                    <input placeholder='Price' value={price} onChange={e => setPrice(e.target.value)}></input>
                    
                    <button className='button' type='submit'>Add Book</button>
                </form>
            </div>
        </div>
    );
}