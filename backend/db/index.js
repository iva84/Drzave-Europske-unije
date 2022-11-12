const {Pool} = require('pg');

const pool = new Pool({
    user: 'postgres',
    host: 'localhost',
    database: "drzaveEU", 
    password: 'bazepodataka',
    port: 5432,
});

module.exports = {
    query: async (text, params) => {
        const res = await pool.query(text, params);
       // console.log({ text, params, rows: res.rows });
        return res;
    }
}