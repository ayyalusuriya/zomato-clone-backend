import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <header className="absolute top-0 left-0 w-full z-50">

      <div className="max-w-7xl mx-auto flex justify-between items-center py-6 px-8">

        <Link
          to="/"
          className="text-white text-4xl font-bold tracking-wide"
        >
          Zomato
        </Link>

        <div className="flex gap-8 text-white text-lg">

          <Link to="/login" className="hover:text-red-300 transition">
            Login
          </Link>

          <Link
            to="/register"
            className="bg-red-500 px-5 py-2 rounded-full hover:bg-red-600 transition"
          >
            Sign Up
          </Link>

        </div>

      </div>

    </header>
  );
}