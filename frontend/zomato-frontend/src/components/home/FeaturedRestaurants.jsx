export default function FeaturedRestaurants() {

    return (
  
      <section className="max-w-7xl mx-auto py-20">
  
        <h2 className="text-4xl font-bold mb-12">
          Featured Restaurants
        </h2>
  
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
  
          {[1,2,3,4,5,6].map((item)=>(
            <div
              key={item}
              className="rounded-3xl overflow-hidden shadow-xl hover:-translate-y-2 transition bg-white"
            >
  
              <img
                src="https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=800"
                className="h-60 w-full object-cover"
                alt=""
              />
  
              <div className="p-6">
  
                <h3 className="text-2xl font-semibold">
                  Restaurant {item}
                </h3>
  
                <p className="text-gray-500 mt-2">
                  North Indian • Chinese • Fast Food
                </p>
  
                <div className="flex justify-between mt-4">
  
                  <span className="text-green-600 font-bold">
                    ⭐ 4.5
                  </span>
  
                  <span>
                    30 mins
                  </span>
  
                </div>
  
              </div>
  
            </div>
          ))}
  
        </div>
  
      </section>
  
    );
  
  }