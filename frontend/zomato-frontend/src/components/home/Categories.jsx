export default function Categories() {
    return (
      <section className="max-w-7xl mx-auto py-20">
  
        <h2 className="text-4xl font-bold mb-10">
          Popular Categories
        </h2>
  
        <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-6 gap-8">
  
          {[
            "🍕 Pizza",
            "🍔 Burger",
            "🍜 Chinese",
            "🥗 Healthy",
            "🍰 Dessert",
            "☕ Cafe",
          ].map((item) => (
            <div
              key={item}
              className="bg-white rounded-2xl shadow-lg p-8 text-center hover:scale-105 transition cursor-pointer"
            >
              <h3 className="text-xl font-semibold">
                {item}
              </h3>
            </div>
          ))}
  
        </div>
  
      </section>
    );
  }