package com.home.tateana.logicgame.quiz;

import com.home.tateana.logicgame.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tateana on 15-Jul-15.
 */
public class WordCategoriesStorage {

    private int numberOfCategories = 12;

    public int getNumberOfCategories() {
        return numberOfCategories;
    }


    public int getCategoryByIndex(int index) {
        return getCategory(index).getName();
    }

    public int getCategorySizeByIndex(int index) {
        return getCategory(index).getSize();
    }

    public int getWordByIndexAndCategoryIndex(int categoryIndex, int wordIndex) {
        List<Integer> words = getWordListByCategoryIndex(categoryIndex);
        if(words.size() <= wordIndex) {
            wordIndex = convertIndex(wordIndex, words.size());
        }
        return words.get(wordIndex);
    }

    public List<Integer> getWordListByCategoryIndex(int categoryIndex) {
        return getCategory(categoryIndex).getWords();
    }

    protected int convertIndex(int index, int maxIndex) {
        return index % maxIndex;
    }

    protected Category getCategory(int index) {
        switch (index) {
            case 0:
                return new Actions();
            case 1:
                return new Clothes();
            case 2:
                return new Colors();
            case 3:
                return new Fruits();
            case 4:
                return new Furniture();
            case 5:
                return new HumanQualities();
            case 6:
                return new Insects();
            case 7:
                return new Pets();
            case 8:
                return new Tableware();
            case 9:
                return new Vegetables();
            case 10:
                return new Birds();
            case 11:
                return new WildAnimals();
            default:
               return getCategory(convertIndex(index, numberOfCategories));
        }
    }

    private static interface Category {
        int getName();
        List getWords();
        int getSize();
    }

    private static class Actions implements Category {

        @Override
        public int getName() {
            return R.string.material_action;
        }

        @Override
        public List getWords() {
            ArrayList<Integer> list = new ArrayList();
            list.add(R.string.material_action_carry);
            list.add(R.string.material_action_scare);
            list.add(R.string.material_action_run);
            list.add(R.string.material_action_crawl);
            list.add(R.string.material_action_drag);
            list.add(R.string.material_action_push);
            list.add(R.string.material_action_throw);
            list.add(R.string.material_action_hit);
            list.add(R.string.material_action_close);
            list.add(R.string.material_action_open);
            list.add(R.string.material_action_hide);
            list.add(R.string.material_action_see);
            list.add(R.string.material_action_sniff);
            list.add(R.string.material_action_cry);
            list.add(R.string.material_action_speak);
            list.add(R.string.material_action_write);
            list.add(R.string.material_action_think);
            return list;
        }

        @Override
        public int getSize() {
            return 17;
        }
    }

    private class Clothes implements Category {
        @Override
        public int getName() {
            return R.string.material_clothes;
        }

        @Override
        public List getWords() {
            ArrayList<Integer> list = new ArrayList();
            list.add(R.string.material_clothes_skirt);
            list.add(R.string.material_clothes_dress);
            list.add(R.string.material_clothes_blouse);
            list.add(R.string.material_clothes_trousers);
            list.add(R.string.material_clothes_socks);
            list.add(R.string.material_clothes_tights);
            list.add(R.string.material_clothes_swimsuit);
            list.add(R.string.material_clothes_sweater);
            list.add(R.string.material_clothes_shorts);
            list.add(R.string.material_clothes_jeans);
            list.add(R.string.material_clothes_T_shirt);
            list.add(R.string.material_clothes_hat);
            list.add(R.string.material_clothes_jacket);
            list.add(R.string.material_clothes_fur_coat);
            list.add(R.string.material_clothes_raincoat);
            list.add(R.string.material_clothes_gloves);
            list.add(R.string.material_clothes_scarf);
            list.add(R.string.material_clothes_cardigan);
            list.add(R.string.material_clothes_belt);
            return list;
        }

        @Override
        public int getSize() {
            return 19;
        }
    }

    private class Colors implements Category {
        @Override
        public int getName() {
            return R.string.material_color;
        }

        @Override
        public List getWords() {
            ArrayList<Integer> list = new ArrayList();
            list.add(R.string.material_red);
            list.add(R.string.material_purple);
            list.add(R.string.material_blue);
            list.add(R.string.material_light_blue);
            list.add(R.string.material_green);
            list.add(R.string.material_yellow);
            list.add(R.string.material_orange);
            list.add(R.string.material_brown);
            list.add(R.string.material_grey);
            return list;
        }

        @Override
        public int getSize() {
            return 9;
        }
    }

    private class Fruits implements Category {
        @Override
        public int getName() {
            return R.string.material_fruit;
        }

        @Override
        public List getWords() {
            ArrayList<Integer> list = new ArrayList();
            list.add(R.string.material_fruit_apricot);
            list.add(R.string.material_fruit_pineapple);
            list.add(R.string.material_fruit_banana);
            list.add(R.string.material_fruit_grape);
            list.add(R.string.material_fruit_grapefruit);
            list.add(R.string.material_fruit_pear);
            list.add(R.string.material_fruit_melon);
            list.add(R.string.material_fruit_lemon);
            list.add(R.string.material_fruit_mandarin);
            list.add(R.string.material_fruit_peach);
            list.add(R.string.material_fruit_plum);
            list.add(R.string.material_fruit_apple);
            list.add(R.string.material_fruit_kiwi);
            list.add(R.string.material_fruit_mango);
            list.add(R.string.material_fruit_persimmon);
            return list;
        }

        @Override
        public int getSize() {
            return 15;
        }
    }

    private class Furniture implements Category {
        @Override
        public int getName() {
            return R.string.material_furniture;
        }

        @Override
        public List getWords() {
            ArrayList<Integer> list = new ArrayList();
            list.add(R.string.material_furniture_bookcase);
            list.add(R.string.material_furniture_cupboard);
            list.add(R.string.material_furniture_shelf);
            list.add(R.string.material_furniture_table);
            list.add(R.string.material_furniture_chair);
            list.add(R.string.material_furniture_bed);
            list.add(R.string.material_furniture_stool);
            list.add(R.string.material_furniture_mirror);
            list.add(R.string.material_furniture_bureau);
            list.add(R.string.material_furniture_bench);
            return list;
        }

        @Override
        public int getSize() {
            return 10;
        }
    }

    private class HumanQualities implements Category {
        @Override
        public int getName() {
            return R.string.material_human_quality;
        }

        @Override
        public List getWords() {
            ArrayList<Integer> list = new ArrayList();
            list.add(R.string.material_human_quality_thin);
            list.add(R.string.material_human_quality_fat);
            list.add(R.string.material_human_quality_tall);
            list.add(R.string.material_human_quality_low);
            list.add(R.string.material_human_quality_weak);
            list.add(R.string.material_human_quality_strong);
            list.add(R.string.material_human_quality_old);
            list.add(R.string.material_human_quality_young);
            list.add(R.string.material_human_quality_hairy);
            list.add(R.string.material_human_quality_bald);
            list.add(R.string.material_human_quality_handsome);
            list.add(R.string.material_human_quality_ugly);
            list.add(R.string.material_human_quality_clever);
            list.add(R.string.material_human_quality_stupid);
            list.add(R.string.material_human_quality_evil);
            list.add(R.string.material_human_quality_kind);
            list.add(R.string.material_human_quality_greedy);
            list.add(R.string.material_human_quality_generous);
            list.add(R.string.material_human_quality_rude);
            list.add(R.string.material_human_quality_polite);
            list.add(R.string.material_human_quality_married);
            list.add(R.string.material_human_quality_single);
            list.add(R.string.material_human_quality_rich);
            list.add(R.string.material_human_quality_poor);
            list.add(R.string.material_human_quality_active);
            list.add(R.string.material_human_quality_passive);
            return list;
        }

        @Override
        public int getSize() {
            return 26;
        }
    }

    private class Insects implements Category {
        @Override
        public int getName() {
            return R.string.material_insect;
        }

        @Override
        public List getWords() {
            ArrayList<Integer> list = new ArrayList();
            list.add(R.string.material_insect_fly);
            list.add(R.string.material_insect_mosquito);
            list.add(R.string.material_insect_flea);
            list.add(R.string.material_insect_moth);
            list.add(R.string.material_insect_dragonfly);
            list.add(R.string.material_insect_spider);
            list.add(R.string.material_insect_wasp);
            list.add(R.string.material_insect_tick);
            list.add(R.string.material_insect_bee);
            list.add(R.string.material_insect_caterpillar);
            list.add(R.string.material_insect_butterfly);
            list.add(R.string.material_insect_ant);
            list.add(R.string.material_insect_grasshopper);
            list.add(R.string.material_insect_bug);
            list.add(R.string.material_insect_cockroach);
            list.add(R.string.material_insect_scorpion);
            list.add(R.string.material_insect_mantis);
            list.add(R.string.material_insect_snail);
            list.add(R.string.material_insect_worm);
            return list;
        }

        @Override
        public int getSize() {
            return 19;
        }
    }

    private class Pets implements Category {
        @Override
        public int getName() {
            return R.string.material_pet;
        }

        @Override
        public List getWords() {
            ArrayList<Integer> list = new ArrayList();
            list.add(R.string.material_pet_pig);
            list.add(R.string.material_pet_horse);
            list.add(R.string.material_pet_donkey);
            list.add(R.string.material_pet_cow);
            list.add(R.string.material_pet_bull);
            list.add(R.string.material_pet_cat);
            list.add(R.string.material_pet_dog);
            list.add(R.string.material_pet_sheep);
            list.add(R.string.material_pet_goat);
            list.add(R.string.material_pet_foal);
            list.add(R.string.material_pet_rabbit);
            return list;
        }

        @Override
        public int getSize() {
            return 11;
        }
    }

    private class Tableware implements Category {
        @Override
        public int getName() {
            return R.string.material_tableware;
        }

        @Override
        public List getWords() {
            ArrayList<Integer> list = new ArrayList();
            list.add(R.string.material_tableware_plate);
            list.add(R.string.material_tableware_bowl);
            list.add(R.string.material_tableware_cup);
            list.add(R.string.material_tableware_mug);
            list.add(R.string.material_tableware_fork);
            list.add(R.string.material_tableware_knife);
            list.add(R.string.material_tableware_glass);
            list.add(R.string.material_tableware_saucepan);
            list.add(R.string.material_tableware_jug);
            list.add(R.string.material_tableware_teapot);
            list.add(R.string.material_tableware_coffeepot);
            list.add(R.string.material_tableware_pan);
            list.add(R.string.material_tableware_bottle);
            list.add(R.string.material_tableware_lid);
            return list;
        }

        @Override
        public int getSize() {
            return 14;
        }
    }

    private class Vegetables implements Category {
        @Override
        public int getName() {
            return R.string.material_vegetable;
        }

        @Override
        public List getWords() {
            ArrayList<Integer> list = new ArrayList();
            list.add(R.string.material_vegetable_aubergine);
            list.add(R.string.material_vegetable_bean);
            list.add(R.string.material_vegetable_pea);
            list.add(R.string.material_vegetable_cabbage);
            list.add(R.string.material_vegetable_onion);
            list.add(R.string.material_vegetable_carrot);
            list.add(R.string.material_vegetable_cucumber);
            list.add(R.string.material_vegetable_tomato);
            list.add(R.string.material_vegetable_pepper);
            list.add(R.string.material_vegetable_radish);
            list.add(R.string.material_vegetable_beet);
            list.add(R.string.material_vegetable_garlic);
            list.add(R.string.material_vegetable_pumpkin);
            return list;
        }

        @Override
        public int getSize() {
            return 13;
        }
    }

    private class Birds implements Category {
        @Override
        public int getName() {
            return R.string.material_bird;
        }

        @Override
        public List getWords() {
            ArrayList<Integer> list = new ArrayList();
            list.add(R.string.material_bird_crow);
            list.add(R.string.material_bird_flamingo);
            list.add(R.string.material_bird_seagull);
            list.add(R.string.material_bird_sparrow);
            list.add(R.string.material_bird_woodpecker);
            list.add(R.string.material_bird_owl);
            list.add(R.string.material_bird_ostrich);
            list.add(R.string.material_bird_penguin);
            list.add(R.string.material_bird_peacock);
            list.add(R.string.material_bird_chicken);
            list.add(R.string.material_bird_cock);
            list.add(R.string.material_bird_swan);
            list.add(R.string.material_bird_goose);
            list.add(R.string.material_bird_duck);
            list.add(R.string.material_bird_parakeet);
            list.add(R.string.material_bird_eagle);
            list.add(R.string.material_bird_hawk);
            list.add(R.string.material_bird_pigeon);
            list.add(R.string.material_bird_stork);
            return list;
        }

        @Override
        public int getSize() {
            return 19;
        }
    }

    private class WildAnimals implements Category {
        @Override
        public int getName() {
            return R.string.material_wild_animal;
        }

        @Override
        public List getWords() {
            ArrayList<Integer> list = new ArrayList();
            list.add(R.string.material_wild_animal_bear);
            list.add(R.string.material_wild_animal_fox);
            list.add(R.string.material_wild_animal_rabbit);
            list.add(R.string.material_wild_animal_boar);
            list.add(R.string.material_wild_animal_deer);
            list.add(R.string.material_wild_animal_moose);
            list.add(R.string.material_wild_animal_giraffe);
            list.add(R.string.material_wild_animal_hippopotamus);
            list.add(R.string.material_wild_animal_zebra);
            list.add(R.string.material_wild_animal_kangaroo);
            list.add(R.string.material_wild_animal_elephant);
            list.add(R.string.material_wild_animal_leopard);
            list.add(R.string.material_wild_animal_lion);
            list.add(R.string.material_wild_animal_tiger);
            list.add(R.string.material_wild_animal_rat);
            list.add(R.string.material_wild_animal_raccoon);
            list.add(R.string.material_wild_animal_squirrel);
            list.add(R.string.material_wild_animal_beaver);
            list.add(R.string.material_wild_animal_mouse);
            list.add(R.string.material_wild_animal_monkey);
            return list;
        }

        @Override
        public int getSize() {
            return 20;
        }
    }
}
