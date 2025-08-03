-- Step 1: Create the database
CREATE DATABASE IF NOT EXISTS watchlist_db;

-- Step 2: Use the database
USE watchlist_db;

-- Step 3: Create the watchlist table
CREATE TABLE IF NOT EXISTS watchlist (
    id VARCHAR(5) PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    type VARCHAR(20),
    status VARCHAR(20),
    rating INT CHECK (rating BETWEEN 1 AND 5)
);

-- Step 4: Insert 50 unique entries (25 Movies + 25 Web-Series)
INSERT INTO watchlist (id, title, type, status, rating) VALUES
('M01', 'Pushpa', 'Movie', 'Watched', 5),
('M02', 'Vivah', 'Movie', 'Not Watched', 3),
('M03', 'KGF', 'Movie', 'Watched', 5),
('M04', 'Baahubali', 'Movie', 'Watched', 4),
('M05', 'Shershaah', 'Movie', 'Not Watched', 3),
('M06', 'Drishyam', 'Movie', 'Watched', 5),
('M07', 'Dangal', 'Movie', 'Not Watched', 4),
('M08', '3 Idiots', 'Movie', 'Watched', 5),
('M09', 'Pathaan', 'Movie', 'Not Watched', 3),
('M10', 'War', 'Movie', 'Watched', 2),
('M11', 'Raazi', 'Movie', 'Watched', 4),
('M12', 'Kahaani', 'Movie', 'Not Watched', 2),
('M13', 'Chhichhore', 'Movie', 'Watched', 4),
('M14', 'Rockstar', 'Movie', 'Watched', 3),
('M15', 'Brahmastra', 'Movie', 'Not Watched', 5),
('M16', 'RRR', 'Movie', 'Watched', 4),
('M17', 'Gully Boy', 'Movie', 'Not Watched', 2),
('M18', 'Article 15', 'Movie', 'Watched', 3),
('M19', 'Super 30', 'Movie', 'Watched', 4),
('M20', 'Airlift', 'Movie', 'Not Watched', 3),
('M21', 'Uri', 'Movie', 'Watched', 4),
('M22', 'Bhool Bhulaiyaa', 'Movie', 'Not Watched', 3),
('M23', 'PK', 'Movie', 'Watched', 5),
('M24', 'Barfi', 'Movie', 'Not Watched', 4),
('M25', 'Tumbbad', 'Movie', 'Watched', 5),
('W01', 'Family Man', 'Web-Series', 'Watched', 5),
('W02', 'Kota Factory', 'Web-Series', 'Not Watched', 4),
('W03', 'Mirzapur', 'Web-Series', 'Watched', 5),
('W04', 'Scam 1992', 'Web-Series', 'Watched', 5),
('W05', 'Panchayat', 'Web-Series', 'Not Watched', 4),
('W06', 'Sacred Games', 'Web-Series', 'Watched', 4),
('W07', 'Delhi Crime', 'Web-Series', 'Not Watched', 5),
('W08', 'Asur', 'Web-Series', 'Watched', 4),
('W09', 'Made in Heaven', 'Web-Series', 'Not Watched', 3),
('W10', 'TVF Pitchers', 'Web-Series', 'Watched', 5),
('W11', 'Special Ops', 'Web-Series', 'Watched', 5),
('W12', 'Breathe', 'Web-Series', 'Not Watched', 3),
('W13', 'Criminal Justice', 'Web-Series', 'Watched', 4),
('W14', 'Hostel Daze', 'Web-Series', 'Not Watched', 3),
('W15', 'Rana Naidu', 'Web-Series', 'Watched', 4),
('W16', 'Farzi', 'Web-Series', 'Not Watched', 4),
('W17', 'Rocket Boys', 'Web-Series', 'Watched', 5),
('W18', 'Mumbai Diaries', 'Web-Series', 'Not Watched', 3),
('W19', 'Jamtara', 'Web-Series', 'Watched', 4),
('W20', 'Grahan', 'Web-Series', 'Watched', 5),
('W21', 'Engineering Girls', 'Web-Series', 'Not Watched', 3),
('W22', 'Little Things', 'Web-Series', 'Watched', 4),
('W23', 'Tripling', 'Web-Series', 'Watched', 4),
('W24', 'Cubicles', 'Web-Series', 'Not Watched', 3),
('W25', 'Undekhi', 'Web-Series', 'Watched', 5);

SELECT * FROM watchlist;
