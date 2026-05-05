import React, { useState } from 'react';

const createAuthHeader = (credentials) => (
  `Basic ${window.btoa(`${credentials.username}:${credentials.password}`)}`
);

function App() {
  const [credentials, setCredentials] = useState(null);
  const [loginForm, setLoginForm] = useState({ username: '', password: '' });
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(false);
  const [loggingIn, setLoggingIn] = useState(false);
  const [error, setError] = useState(null);

  const loadStudents = (authCredentials) => {
    setLoading(true);
    setError(null);

    return fetch('https://codexdemo.onrender.com/api/students', {
      headers: {
        Authorization: createAuthHeader(authCredentials),
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Failed to fetch students');
        }
        return response.json();
      })
      .then((data) => {
        setStudents(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
        throw err;
      });
  };

  const handleLoginChange = (event) => {
    setLoginForm({
      ...loginForm,
      [event.target.name]: event.target.value,
    });
  };

  const handleLogin = (event) => {
    event.preventDefault();
    setLoggingIn(true);
    setError(null);

    loadStudents(loginForm)
      .then(() => {
        setCredentials(loginForm);
        setLoginForm({ username: '', password: '' });
      })
      .catch(() => {
        setError('Invalid username or password');
      })
      .finally(() => {
        setLoggingIn(false);
      });
  };

  const handleLogout = () => {
    setCredentials(null);
    setStudents([]);
    setError(null);
  };

  if (!credentials) {
    return (
      <main style={styles.loginPage}>
        <form style={styles.loginPanel} onSubmit={handleLogin}>
          <h1 style={styles.title}>Student Management</h1>
          <label style={styles.label} htmlFor="username">Username</label>
          <input
            id="username"
            name="username"
            value={loginForm.username}
            onChange={handleLoginChange}
            style={styles.input}
            autoComplete="username"
            required
          />
          <label style={styles.label} htmlFor="password">Password</label>
          <input
            id="password"
            name="password"
            type="password"
            value={loginForm.password}
            onChange={handleLoginChange}
            style={styles.input}
            autoComplete="current-password"
            required
          />
          {error && <div style={styles.error}>{error}</div>}
          <button type="submit" style={styles.primaryButton} disabled={loggingIn}>
            {loggingIn ? 'Signing in...' : 'Sign in'}
          </button>
        </form>
      </main>
    );
  }

  return (
    <div style={styles.app}>
      <header style={styles.header}>
        <h1 style={styles.appTitle}>Student Management</h1>
        <button type="button" style={styles.secondaryButton} onClick={handleLogout}>
          Sign out
        </button>
      </header>
      {loading && <div>Loading students...</div>}
      {error && <div style={styles.error}>{error}</div>}
      {!loading && !error && (
        <div>
          <h2>Students</h2>
          <ul style={styles.list}>
            {students.map((student) => (
              <li key={student.id} style={styles.listItem}>
                {student.firstName} {student.lastName} ({student.email})
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}

const styles = {
  loginPage: {
    minHeight: '100vh',
    display: 'grid',
    placeItems: 'center',
    background: '#f3f5f8',
    fontFamily: 'Arial, sans-serif',
    padding: '1rem',
  },
  loginPanel: {
    width: '100%',
    maxWidth: '360px',
    display: 'flex',
    flexDirection: 'column',
    gap: '0.75rem',
    background: '#ffffff',
    border: '1px solid #d9dee7',
    borderRadius: '8px',
    padding: '1.5rem',
  },
  title: {
    margin: '0 0 0.5rem',
    fontSize: '1.5rem',
  },
  label: {
    fontSize: '0.9rem',
    fontWeight: 700,
  },
  input: {
    minHeight: '2.5rem',
    border: '1px solid #b8c0cc',
    borderRadius: '6px',
    padding: '0 0.75rem',
    fontSize: '1rem',
  },
  primaryButton: {
    minHeight: '2.5rem',
    border: 0,
    borderRadius: '6px',
    background: '#1f6feb',
    color: '#ffffff',
    fontWeight: 700,
    cursor: 'pointer',
  },
  secondaryButton: {
    minHeight: '2.25rem',
    border: '1px solid #b8c0cc',
    borderRadius: '6px',
    background: '#ffffff',
    color: '#1f2937',
    fontWeight: 700,
    cursor: 'pointer',
    padding: '0 0.85rem',
  },
  app: {
    padding: '2rem',
    fontFamily: 'Arial, sans-serif',
  },
  header: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    gap: '1rem',
  },
  appTitle: {
    margin: 0,
  },
  error: {
    color: '#b42318',
  },
  list: {
    paddingLeft: '1.25rem',
  },
  listItem: {
    marginBottom: '0.5rem',
  },
};

export default App;
