import { Link } from 'react-router-dom';
import { FiArrowLeft } from 'react-icons/fi';

import './styles.css';

import logoImage from '../../assets/logo.svg'


export default function Books() {
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
                <form>
                    <input placeholder='Title'></input>
                    <input placeholder='Author'></input>
                    <input type="date"></input>
                    <input placeholder='Price'></input>
                    
                    <button className='button' type='submit'>Add Book</button>
                </form>
            </div>
        </div>
    );
}