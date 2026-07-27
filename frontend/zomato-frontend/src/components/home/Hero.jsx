import hero from "../../assets/hero.png";

export default function Hero() {

  return (

    <section
      className="relative h-screen bg-cover bg-center"
      style={{
        backgroundImage: `url(${hero})`,
      }}
    >

      <div className="absolute inset-0 bg-black/50"></div>

      <div className="relative z-10 flex flex-col justify-center items-center h-full text-center px-4">

        <h1 className="text-6xl font-bold text-white">
          Discover the best food
        </h1>

        <h2 className="text-6xl font-bold text-white mt-2">
          & drinks near you
        </h2>

        <div className="bg-white rounded-2xl mt-12 flex w-full max-w-3xl overflow-hidden shadow-2xl">

          <input
            type="text"
            placeholder="Search for restaurants, cuisines..."
            className="flex-1 px-6 py-5 text-lg outline-none"
          />

          <button className="bg-red-500 px-10 text-white font-semibold hover:bg-red-600 transition">
            Search
          </button>

        </div>

      </div>

    </section>

  );

}