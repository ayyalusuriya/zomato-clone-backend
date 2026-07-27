import MainLayout from "../layouts/MainLayout";
import Hero from "../components/home/Hero";
import Categories from "../components/home/Categories";
import FeaturedRestaurants from "../components/home/FeaturedRestaurants";

export default function Home() {
  return (
    <MainLayout>
      <Hero />
      <Categories />
      <FeaturedRestaurants />
    </MainLayout>
  );
}