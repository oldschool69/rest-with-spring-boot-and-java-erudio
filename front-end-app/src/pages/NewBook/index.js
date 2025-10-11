import { Link, useParams } from 'react-router-dom';
import { FiArrowLeft } from 'react-icons/fi';
import { useHistory } from 'react-router-dom';
import { useState, useEffect } from 'react';
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

    const bookId = useParams().bookId;

    useEffect(() => {
        if (bookId === '0') {
            return;
        }
        loadBook();
    }, [bookId]);

    async function loadBook() {
        const token = localStorage.getItem('accessToken');
        try {
            const response = await api.get(`/book/${bookId}`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                }
            });
            const adjustedDate = new Date(response.data.launchDate).toISOString().split('T')[0];
            setId(response.data.id);
            setTitle(response.data.title);
            setAuthor(response.data.author);
            setPrice(response.data.price);
            setLaunchDate(adjustedDate);
        }   catch (err) {
            alert('Error loading book, try again.');
            history.push('/books');
        }
    }
        
    async function saveOrUpdate(e){
        e.preventDefault();

        const token = localStorage.getItem('accessToken');

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
            if (bookId === '0') {
                await api.post('/book', data, { headers: headers });
            } else {
                data.id = id;
                await api.put('/book', data, { headers: headers });
            }
            history.push('/books');
        } catch (err) {
            alert('Error creating new book, try again.');
        }
    }

    function getLabel() {
        return bookId === '0' ? 'Add New Book' : 'Edit Book';
    }

    return (
        <div className="new-book-container">
            <div className="content">
                <section className='form'>
                    <img src={logoImage} alt="Carecao Logo"></img>
                    <h1>{getLabel()}</h1>
                    <p>Fill in the details below to add a new book to the collection.</p>
                    <Link className="back-link" to="/books">
                        <FiArrowLeft size={16} color="#251FC5" />
                        Back to Books
                    </Link>
                </section>
                <form onSubmit={saveOrUpdate}>
                    <input placeholder='Title' value={title} onChange={e => setTitle(e.target.value)}></input>
                    <input placeholder='Author' value={author} onChange={e => setAuthor(e.target.value)}></input>
                    <input type="date" value={launchDate} onChange={e => setLaunchDate(e.target.value)}></input>
                    <input placeholder='Price' value={price} onChange={e => setPrice(e.target.value)}></input>

                    <button className='button' type='submit'>{getLabel()}</button>
                </form>
            </div>
        </div>
    );
}